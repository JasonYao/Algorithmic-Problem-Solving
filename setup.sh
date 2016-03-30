#!/usr/bin/env bash

set -e

##
# Setup script to create the necessary symlinks to make things in the future easier (can also be manually done)
##

###
# Helper functions
##
info () {
    printf "\r  [ \033[00;34m..\033[0m ] $1\n"
}
user () {
    printf "\r  [ \033[0;33m?\033[0m ] $1 "
}
success () {
    printf "\r\033[2K  [ \033[00;32mOK\033[0m ] $1\n"
}
fail () {
  printf "\r\033[2K  [\033[0;31mFAIL\033[0m] $1\n"
  echo ''
  exit
}

absRootDir="$(pwd)"

# Checks if recitation symlink is already installed
recitations_link="$absRootDir/recitations/JavaBoilerplate"
    if [ -d "$recitations_link" ];
    then
        info "Recitations symlink already installed"
    else
        ln -s $absRootDir/JavaBoilerplate/ $recitations_link
        success "Recitations symlink installed"
    fi

# Checks if homework symlink is already installed
homeworks_link="$absRootDir/homeworks/JavaBoilerplate"
    if [ -d "$homeworks_link" ];
    then
        info "Homework symlink already installed"
    else
        ln -s $absRootDir/JavaBoilerplate/ $homeworks_link
        success "Homeworks symlink installed"
    fi
