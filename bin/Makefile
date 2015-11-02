JAVA = "C:\Program Files\Java\jdk1.7.0_25\bin\java.exe"
JAVAC = "C:\Program Files\Java\jdk1.7.0_25\bin\javac.exe"
OPENJMLJAR = "C:\Program Files (x86)\OpenJML\openjml.jar"
JMLC = $(JAVA) -jar $(OPENJMLJAR)  -rac -nopuritycheck -racCheckAssumptions -racPreconditionEntry -d ..\bin
JML = $(JAVA) -jar $(OPENJMLJAR) -nopuritycheck 
RM = rm
JUNIT4 = "C:\Program Files (x86)\JUnit\junit4.12\junit-4.12.jar";"C:\Program Files (x86)\JUnit\junit4.12\hamcrest-core-1.3.jar"
JMLRUNTIME = "C:\Program Files (x86)\OpenJML\jmlruntime.jar"
JMLCLASSPATH = .;$(JMLRUNTIME)
JMLJUNITCLASSPATH = $(JMLCLASSPATH);$(JUNIT4)

run : ..\bin\ExplosivesMain.class
	$(JAVA) -cp ..\bin;$(JMLCLASSPATH) ExplosivesMain

..\bin\Explosives.class : Explosives.java
	$(JMLC) -d ..\bin Explosives.java

..\bin\ExplosivesMain.class : ..\bin\Explosives.class ExplosivesMain.java
	$(JAVAC) -cp ..\bin;$(JMLCLASSPATH) -d ..\bin  ExplosivesMain.java


..\bin\TestExplosivesJUnit4.class : ..\bin\Explosives.class TestExplosivesJUnit4.java
	$(JAVAC) -cp ..\bin;$(JMLJUNITCLASSPATH) -d ..\bin  TestExplosivesJUnit4.java

TestExplosivesJUnit4.run : ..\bin\TestExplosivesJUnit4.class
	 $(JAVA) -cp ..\bin;$(JMLJUNITCLASSPATH) TestExplosivesJUnit4

clean : 
	$(RM) ..\\bin\\*.class
