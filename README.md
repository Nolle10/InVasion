# InVasion

![](https://github.com/kolorobot/spring-boot-junit5/workflows/tests/badge.svg)

InVasion is a tower defense game developed during the 4th semester at SDU. In this game, players must strategically place towers to defend against waves of invading enemies. With challenging gameplay and a variety of tower and enemy types based on biology and human anatomy.



# Setup Guide


## Step 1:
  locate the folder .m2 on your computer.
  change the file inside the folder called settings.xml
  with the the example located below.
  Note that some values may be needed to be changed
  ```
  <settings xmlns="http://maven.apache.org/SETTINGS/1.0.0" 
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                              http://maven.apache.org/xsd/settings-1.0.0.xsd">
  
    <activeProfiles>
        <activeProfile>github</activeProfile>
    </activeProfiles>
  
    <profiles>
        <profile>
            <id>github</id>
            <repositories>
                <repository>
                    <id>central</id>
                    <url>https://repo1.maven.org/maven2</url>
                </repository>
                 <repository>
                    <id>github</id>
                    <url>https://maven.pkg.github.com/Crazi12345/ShadedLibGDX</url>
                    <snapshots>
                        <enabled>true</enabled>
                    </snapshots>
                </repository>
            </repositories>
        </profile>
    </profiles>
  
    <servers>
        <server>
            <id>github</id>
            <username>GITHUB USERNAME</username>
	    <password>GITHUB TOKEN</password>
        </server>
    </servers>
  
</settings>
```

## Step 2
### Open IDE
Now you should open your IDE or Terminal and run the following commands

```
mvn clean install
mvn exec:exec
```




