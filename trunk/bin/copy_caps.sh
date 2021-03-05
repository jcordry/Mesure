#!/bin/sh -

echo copying cap files from src tree to classes tree.
cd ../src
find . -name \*.cap -exec cp {} ../classes/{} \;

