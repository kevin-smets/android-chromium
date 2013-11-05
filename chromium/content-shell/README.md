
This is an Android application that builds Chromium Content Shell -- Content Shell is the base browser implementation for Chromium

Modifications: see ./modified directory
  * ContentShellApplication.java modified to tell LibraryLoader.java which native library to load
  * strings.xml was hand-merged from two separate sources
  * AndroidManifest.xml removed the application icon attribute as it failed to find the requested resource
