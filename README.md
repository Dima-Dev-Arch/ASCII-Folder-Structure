# ASCII Folder Structure
This program generates an **ASCII representation of a folder structure**
and saves the result to a file.
And it is customizable! Inside **_src.com.project.Data_** has Console class and it store output data!

---
Example output:
```txt
 C:/User/Admin/Desktop/Projects
|------------------
|-- UntitledProject/
|---- run.exe
|---- ProgramLogic.dll
|-- MyGameWithCoolName/
|---- game.exe
|---- SDL3.dll
|---- Textures/
|------ player_sprite.png
|------ font_atlas.png
|------------------
```

# How to use?
## Compile
```bash
javac -d out/classes src/com/project/*.java
```

## Run
```bash
java -cp src/com/project/*.java <Target Directory> <Output File>
```

**<Target Directory>** - Target directory path (**\_current\_** to use the currect directory as target)
**<Output File>** - Filename where the result will save (**\_nofile\_** to output only in console)