

what is the output 
TERRAGRUNT_CMD+="$TG_WORKDIR"
echo "$TERRAGRUNT_CMD"


#!/bin/bash

# Check if this is a pull request
if [ "$GITHUB_EVENT_NAME" == "pull_request" ]; then
  # Check if the pull request is merged
  if [ "$GITHUB_EVENT_ACTION" == "closed" ] && [ "$GITHUB_EVENT_PULL_REQUEST_MERGED" == "true" ]; then
    echo "Pull request was merged. Proceeding to apply Terraform changes..."
    # Run Terraform apply here
    terraform apply -auto-approve
  else
    echo "Pull request was not merged. Terraform apply is not required."
  fi
else
  echo "This is not a pull request. Terraform apply is not required."
fi

===================================


#!/bin/bash

# Define the branch to monitor for merges
target_branch="main"  # Replace with your desired branch name

# Function to check if the specified branch has been merged
is_branch_merged() {
  local branch_name="$1"
  local remote_name="$2"
  
  # Fetch the latest changes from the remote repository
  git fetch "$remote_name"
  
  # Check if the branch is merged (exit status 0 means merged)
  git branch --list --remote "$remote_name/$branch_name" | grep "$branch_name" &> /dev/null
}

# Check if the target branch has been merged
if is_branch_merged "$target_branch" "origin"; then
  echo "The $target_branch branch has been merged. Proceeding with Terraform apply..."
  
  # Run Terraform apply here
  terraform apply -auto-approve
else
  echo "The $target_branch branch has not been merged. Terraform apply is not required."
fi
=========================


#!/bin/bash

# Define the branch to monitor for merges
target_branch="main"  # Replace with your desired branch name

# Function to check if the specified branch has been merged
is_branch_merged() {
  local branch_name="$1"
  local remote_name="$2"
  
  # Fetch the latest changes from the remote repository
  git fetch "$remote_name"
  
  # Check if the branch is merged (exit status 0 means merged)
  git branch --list --remote "$remote_name/$branch_name" | grep "$branch_name" &> /dev/null
}

# Function to apply Terraform changes and print the list of changed files
apply_terraform_and_print_changes() {
  # Apply Terraform changes
  terraform apply -auto-approve
  
  # Get the list of changed files
  changed_files=$(git diff --name-only)
  
  # Print the list of changed files and count
  echo "Changed files:"
  echo "$changed_files"
  echo "Total changes: $(echo "$changed_files" | wc -l)"
}

# Check if the target branch has been merged
if is_branch_merged "$target_branch" "origin"; then
  echo "The $target_branch branch has been merged. Proceeding with Terraform apply..."
  
  # Run Terraform apply and print changes
  apply_terraform_and_print_changes
else
  echo "The $target_branch branch has not been merged. Terraform apply is not required."
fi
