# Refine Groovy

An [OpenRefine](https://openrefine.org) extension which adds support for [Groovy](https://groovy-lang.org/) as an expression language.

### OpenRefine 3.9.0+ support:
  - [Use Release 1.3.2](https://github.com/thadguidry/refine-groovy/releases/download/1.3.2/refine-groovy.zip)
### OpenRefine 3.5.0+ support:
  - [Use Release 1.3.1](https://github.com/thadguidry/refine-groovy/releases/download/1.3.1/refine-groovy.zip)

# Install

1. Download the `.zip` file of the [latest 1.3.2 release](https://github.com/thadguidry/refine-groovy/releases/download/1.3.2/refine-groovy.zip) of this extension.
2. Unzip the file and place the unzipped `refine-groovy` folder in your OpenRefine extensions folder.
3. For details, read more about [installing extensions in OpenRefine's user manual](https://docs.openrefine.org/manual/installing#installing-extensions).

# Build

Run the following:

```
mvn package
```

This creates a zip file in the target folder, which can then be [installed in OpenRefine](https://docs.openrefine.org/manual/installing#installing-extensions).

# Develop

To avoid having to unzip the extension in the corresponding directory every time you want to test it, you can also use another set up: simply create a symbolic link from your extensions folder in OpenRefine to the local copy of this repository. You will still need to restart OpenRefine every time you make changes.
