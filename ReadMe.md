# k8srolediff User Guide

*Author / Maintainer: torblerone*

## Introduction

The Java tool **k8srolediff** extracts differences between two Kubernetes (*k8s*) roles by given their YAML files. This is useful if you e.g. want to look at the logical differences between an old existing Role and a new Role you just wrote. Using the tool as described below (Section *Usage*), it outputs the diff in a formatted table on the console.

**k8srolediff** is a console program. It is inspired by other **diff** tools. But since others don't show the logical differences between Kubernetes Roles when compared, this little tool might be your helper.


## Usage
You can compile the resources with Maven or just use the given JAR that lies inside this repo.

Start **k8srolediff** with the following command:
`java -jar k8srolediff.jar  [oldRole.yaml] [newRole.yaml]`

*Note* that the two YAML files have to be present in the same directory as the tool itself. Otherwise you will have to give the absolute path of the Role file. You may also parse the YAML to stdin using your `kubectl` like `kubectl get rolebindings.[...]`.


After extracting the differences, the tool outputs a table in following format:
| apiGroup | resource    | verbs1 | verbs2 | diff                                        |
| -------- | ----------- | ------ | ------ | ------------------------------------------- |
| '*'      | configmaps  | 2      | 0      | [- get, - list]                             |
| '*'      | deployments | 5      | 0      | [- get, - list, - patch, - update, - watch] |

What happens is that the tool matches every *apiGroup* with every *resource* and checks the Roles for any matching rules.
If a Role contains the '*' apiGroup, it has access to all resources that are inside that specific rule. Therefore, the Role has access to the resource in every possible apiGroup.