#!/bin/bash
###
### Regenerate java files from .xsd files
###

ROOT=..
LIB=lib
BUILD=classes

CP=$BUILD:$LIB/castor-1.0.3.jar:$LIB/commons-logging-1.1.jar:$LIB/xerces-J_1.4.0.jar



# java -classpath $CP

pushd $ROOT

echo REBUILD src/lib/loader/config/CardConfig
java -cp $CP org.exolab.castor.builder.SourceGenerator -f -dest src -i src/lib/loader/config/CardConfig.xsd -types j2 -package lib.loader.config

echo REBUILD src/tools/manager/config/ManagerConfig
java -cp $CP org.exolab.castor.builder.SourceGenerator -f -dest src -i src/tools/manager/config/ManagerConfig.xsd -types j2 -package tools.manager.config

echo REBUILD src/benchs/lib/config/TestConfig
java -cp $CP org.exolab.castor.builder.SourceGenerator -f -dest src -i src/benchs/lib/config/TestConfig.xsd -types j2 -package benchs.lib.config

echo REBUILDING src/lib/xml/test_result
java -cp $CP org.exolab.castor.builder.SourceGenerator -f -dest src -i src/lib/xml/test_result/TestResult.xsd -types j2 -package lib.xml.test_result

echo REBUILDING src/lib/xml/extractor_result
java -cp $CP org.exolab.castor.builder.SourceGenerator -f -dest src -i src/lib/xml/extractor_result/ExtractorResult.xsd -types j2 -package lib.xml.extractor_result

echo REBUILDING src/lib/xml/profiler_result
java -cp $CP org.exolab.castor.builder.SourceGenerator -f -dest src -i src/lib/xml/profiler_result/fusion.xsd -types j2 -package lib.xml.profiler_result

# echo REBUILDING src/lib/xml/profiler_result
# java -cp $CP org.exolab.castor.builder.SourceGenerator -f -dest src -i src/lib/xml/profiler_result/coefs.xsd -types j2 -package lib.xml.profiler_result

popd