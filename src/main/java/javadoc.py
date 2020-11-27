import os, re

class JavaFile:
    def __init__(self, path, name):
        self.path = path
        self.name = name

class JavaClass:
    def __init__(self, name, package, type_, methods, fields, parents, interfaces):
        self.name = name # the name of the class
        self.package = package # the package it's from
        self.type_ = type_ # class, abstract, interface
        self.methods = methods # a list of methods
        self.fields = fields # a list of fields
        self.parents = parents # a list of what the class inherits
        self.interfaces = interfaces # a list of what the class implements
    
    def __str__(self):
        string = "name: " + self.name + "\npackage: " + self.package + "\ntype: " + self.type_ + "\nparents/interfaces: " + self.parents + "\nmethods: "
        for method in self.methods:
            string += "\n\tname: " + method.name + "\n\tlevel: " + method.accessLevel + "\n\treturns: " + method.returnType + "\n\tparameters: " + str(method.parameters)
        string += "\nfields:"
        for field in self.fields:
            string += "\n\tname: " + field.name + "\n\ttype: " + field.type_ + "\n\tlevel: " + field.level
        return string

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
    def __init__(self, name, type_, level):
        self.name = name
        self.type_ = type_
        self.level = level

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
CLASS_RE = re.compile(r"(public|private|protected) (class|interface) (.+?) ((implements|extends|)(.*))\{") # 1st group is access level, 2nd is type (class/interface), 3rd is class name, 4th is inheritance (either implements/extends), 5th is what it implements or extends
PACKAGE_RE = re.compile(r"(?<=package ).*(?=\;)", re.M)
METHOD_RE = re.compile(r"(private|public) ([A-z]+?) ([A-z]+?(?=\())(.+(?=\) \{))", re.M) # group 1 is access, group 2 is return type, group 3 is name, group 4 is parameters
FIELD_RE = re.compile(r"(private|public) ([A-z]+) (\w+\;|\w+ (?==))", re.M) # group 1 is access, group 2 type, group 3 name (need to strip the last char off of group 3)
INTERFACE_RE = re.compile(r"(?<=implements ).+(?= \{)") # this can't handle multiple implements, so kinda jank for now
for javaFile in fileList[1:]:
    with open(javaFile.path[2:] + "/" + javaFile.name) as f:
        content = f.read()
    try:
        # 0 is level; 1 is type; 2 is name; igore 3; 4 is extends/implements; 5 is whatever is being implemented
        # make sure to strip(). Format is a list containting a single tuple containing the strings
        class_match = re.findall(CLASS_RE, content)
        class_name = class_match[0][2]
        class_type = class_match[0][1]

        # 0 is level; 1 is return type; 2 is name; 3 is params (either a single "(" or a list of parameters)
        # make sure to strip() and remove("(",")"). This one has a list of multiple tuples
        method_match = re.findall(METHOD_RE, content)
        
        # 0 is level; 1 is type; 2 is name
        # strip(), rstrip(";"), replace(" ", "_") b/c underscores are replaced with spaces for some reason
        field_match = re.findall(FIELD_RE, content)

        package_match = re.findall(PACKAGE_RE, content)

        methods = []
        for match in method_match:
            method_name = match[2]
            method_type = match[1]
            method_level = match[0]
            method_pre_params = match[3].strip("( ").split(",")
            method_params = []
            for param in method_pre_params:
                param = param.split(" ")
                if param != [""]:
                    method_params.append(Parameter(param[1], param[0]))
                print(method_params)

            methods.append(Method(method_name, class_name, method_level, method_type, method_params, "comments currently not implemented"))

        fields = []
        for field in fields:
            field_level = field[0]
            field_type = field[1]
            field_name = field[2]
            fields.append(Field(field_name, field_type, field_level)) # change below so we separate parents and interfaces
        classes.append(JavaClass(class_name, package_match[0], class_type, methods, fields, class_match[0][5], class_match[0][5]))
    except Exception as e:
        print("Error ocurred while parsing: " + javaFile.name + " : " + str(e))
#for item in classes:
#    print(item)