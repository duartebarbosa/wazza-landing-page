#!/bin/sh

activator clean cleanFiles clean-files cleanKeepFiles
rm -rf target
rm -rf project/target
rm -rf project/project
rm -rf app-2.11