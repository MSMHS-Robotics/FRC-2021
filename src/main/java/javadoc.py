import os, re

class JavaFile:
    def __init__(self, path, name):
        self.path = path
        self.name = name

class JavaClass:
    def __init__(self, name, package, type_, methods, fields, parents, interfaces):
        self.name = name # the name of the class
        self.package = package # the package it's from
        self.type_ = type_ # class, abstact, interface
        self.methods = methods # a list of methods
        self.fields = fields # a list of fields
        self.parents = parents # a list of what the class inherits
        self.interfaces = interfaces # a list of what the class implements

class Method:
    def __init__(self, name, class_, accessLevel, returnType, parameters, comments):
        self.name = name
        self.class_ = class_
        self.accessLevel = accessLevel
        self.returnType = returnType
        self.parameters = parameters
        self.comments = comments

class Parameter:
    def __init__(self, name, type_):
        self.name = name
        self.type_ = type_

class Field:
    def __init__(self, name, type_):
        self.name = name
        self.type_ = type_

def walk():
    file_list = {}
    for dirpath, dirnames, files in os.walk("."):
            file_list[dirpath] = files
    return file_list

result = walk()
fileList = []
for path, files in result.items():
    for file in files:
        fileList.append(JavaFile(path, file))

classes = []
CLASSNAME_RE = re.compile(r"(?<=class )\w+", re.M)
PACKAGE_RE = re.compile(r"(?<=package ).*(?=\;)", re.M)
TYPE_RE = re.compile(r".* (?=class)")
METHOD_RE = re.compile(r"(private|public) ([A-z]+?) ([A-z]+?(?=\())(.+(?=\) \{))", re.M) # group 1 is access, group 2 is return type, group 3 is name, group 4 is parameters
FIELD_RE = re.compile(r"(private|public) ([A-z]+) (\w+\;|\w+ (?==))", re.M) # group 1 is access, group 2 type, group 3 name (need to strip the last char off of group 3)
PARENT_RE = re.compile(r"(?<=extends ).+(?= \{)") # you can only extend once so we good
INTERFACE_RE = re.compile(r"(?<=implements ).+(?= \{)") # this can't handle multiple implements, so kinda jank for now
for javaFile in fileList[1:]:
    with open(javaFile.path[2:] + "/" + javaFile.name) as f:
        content = f.read()
    try:
        classname = re.findall(CLASSNAME_RE, content)[0] # probably a better way to do this but whatever it's 9:00 pm
        package = re.findall(PACKAGE_RE, content)[0]
        print(package)
    except Exception as e:
        print("Error ocurred while parsing: " + javaFile.name)