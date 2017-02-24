#!/bin/bash
# Go home
cd ~

# Clone git repo
git clone https://github.com/bo0tzz/TopKekBot.git

# Go into TopKekBot directory
cd TopKekBot/

# Pull latest changes
git pull

# Compile
mvn clean package

# Move file
cp -f target/TopKekBot.jar /home/topkek/bots/TopKekBot/TopKekBot.jar

# Back to home
cd ~

# Tell bot update is available
touch ~/bots/TopKekBot/update_available
