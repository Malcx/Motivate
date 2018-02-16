# Motivate

## About

A small app to display a random motivational message every time you log on / return from the windows lock screen. This seemed to be a more consistant method than 


## Usage & configuration

It is assumed you have a functioning install of Java 8 with path/classpath configured.

1. Rename messages_SAMPLE.txt to messages.txt
2. Edit messages.txt to list the quotes you want to see. One per line that will be randomly selected.
3. Edit config.props to change how long the message is displayed in ms (default 2000 == 2 seconds)
4. From a command prompt test with `java Motivate`
5. Edit application.css to amend background colours, or label display properties


## Run on Log on (windows)
Mostly taken from:
https://superuser.com/questions/615114/how-to-make-a-window-task-run-everytime-i-enter-my-password-unlock-the-computer

1. Start Task Scheduler from the start Menu
2. Select the Task Scheduler Library in the left nav pane
3. Select Action > Create Task... (not Create Basic Task...) from the menu bar
4. In the new Create Task window, select the Triggers tab
5. Click on the New... button
6. In the "Begin the task:" drop down, select "On workstation unlock"
7. Repeat and create a second trigger for the task that covers the one you didn't select before
8. Select Actions > New and set the Program/script to `javaw` with the "Add arguments" to `-cp c:\full_path\to_install\folder Motivate`


## Run on Log on (others)

I've not yet needed to set this up on Linux/MacOSX so please let me know if you do and what steps you took.