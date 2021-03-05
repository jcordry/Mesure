trem
rem Regenerate java files from .xsd files
rem

@set HOME=.
@set LIB=%HOME%\lib
@set CLASSES=%HOME%\classes

@set CP=%CLASSES%

@set CP=%CP%;%LIB%\castor-1.0.3.jar
@set CP=%CP%;%LIB%\commons-logging-1.1.jar
@set CP=%CP%;%LIB%\xerces-J_1.4.0.jar

cd ..

@echo REBUILDING src/lib/loader/config/CardConfig
java -cp %CP% org.exolab.castor.builder.SourceGenerator -f -dest src -i src/lib/loader/config/CardConfig.xsd -types j2 -package lib.loader.config

@rem note: use SourceGeneratorMain for 1.0.5+

@rem echo REBUILDING src/benchs/engine/config/ManagerConfig
@rem java -cp %CP% org.exolab.castor.builder.SourceGenerator -f -dest src -i src/benchs/engine/config/ManagerConfig.xsd -types j2 -package benchs.engine.config

@echo REBUILDING src/benchs/lib/config/TestConfig
java -cp %CP% org.exolab.castor.builder.SourceGenerator -f -dest src -i src/benchs/lib/config/TestConfig.xsd -types j2 -package benchs.lib.config

@echo REBUILDING src/lib/xml/test_result
java -cp %CP% org.exolab.castor.builder.SourceGenerator -f -dest src -i src/lib/xml/test_result/TestResult.xsd -types j2 -package lib.xml.test_result

@echo REBUILDING src/lib/xml/extractor_result
java -cp %CP% org.exolab.castor.builder.SourceGenerator -f -dest src -i src/lib/xml/extractor_result/ExtractorResult.xsd -types j2 -package lib.xml.extractor_result

@echo REBUILDING src/lib/xml/profiler_result
java -cp %CP% org.exolab.castor.builder.SourceGenerator -f -dest src -i src/lib/xml/profiler_result/fusion.xsd -types j2 -package lib.xml.profiler_result

@echo REBUILDING src/lib/xml/profiler_result
java -cp %CP% org.exolab.castor.builder.SourceGenerator -f -dest src -i src/lib/xml/profiler_result/coefs.xsd -types j2 -package lib.xml.profiler_result