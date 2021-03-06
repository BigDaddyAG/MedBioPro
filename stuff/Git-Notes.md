# Git-Notes

#### Procedure of adding new changes to a file in the Github repository
`$ git add <file>`  
`$ git commit -m "Commit message (use present tense)"`  
`$ git push`


#### In case of a conflict:
* Revert local changes and pull origin from master  
`$ git checkout -- <file>...` to discard changes in the working directory  
`$ git reset HEAD` to unstage  
`$ git reset --merge` to reset the index and update the files in the working directory

* Save local changes to a stash stack in ordner to not push them to the master  
`$ git stash`

* Remove a single stashed state from the stash stack and apply it on top of the current working tree state  
`$ git stash pop`


#### Other self-explaining commands
`$ git status`  
`$ git log`  
`$ git add -u` update all tracked files in the entire working tree


