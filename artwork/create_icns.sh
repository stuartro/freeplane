#!/bin/bash
mkdir tmp
cp -r Assets.xcassets/AppIcon.appiconset tmp/freeplane.iconset
iconutil -c icns -o outputs/freeplane.icns tmp/freeplane.iconset
rm -rf tmp/freeplane.iconset
rm -rf tmp
