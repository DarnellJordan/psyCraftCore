# psyCraftCore

The Core of the psyCraft Network.
## Compiling
### Adding the `Multiverse-Core` dependency

To be able to compile the project you need to download [Multiverse-Core v4.2.2]("https://dev.bukkit.org/projects/multiverse-core/files/3074594/download") and put it into `src/main/lib`.

If you want to place it somewhere else, you need to changeg the location in the `pom.xml`.

### Changing the output directory

You might want to change the output directory in the `pom.xml`, because you probaly won't have the exact same project structure as I do.

To do this, you need to change `outputDirectory` in the `maven-jar-plugin`.
