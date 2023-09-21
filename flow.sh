#!/bin/bash

# Define the target branch to compare with
target_branch="main"  # Replace with your desired branch name

# Function to get a list of changed files
get_changed_files() {
  git diff --name-only "$target_branch"
}

# Check if the target branch has been merged
if git branch --list --remote "origin/$target_branch" | grep "$target_branch" &> /dev/null; then
  echo "The $target_branch branch has been merged. Proceeding with Terraform apply..."

  # Run Terraform apply here
  terraform apply -auto-approve

  # Get a list of changed files
  changed_files=$(get_changed_files)

  # Count and print the number of changed files
  file_count=$(echo "$changed_files" | wc -l)
  echo "Number of changed files: $file_count"

  # Print the list of changed files
  echo "List of changed files:"
  echo "$changed_files"
else
  echo "The $target_branch branch has not been merged. Terraform apply is not required."
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
====================================


name: Get Absolute Paths of Changed Files

on:
  pull_request:
    types:
      - closed

jobs:
  get_changed_files:
    runs-on: ubuntu-latest
    
    steps:
      - name: Check out code
        uses: actions/checkout@v2

      - name: List changed files
        id: changed-files
        run: |
          # Fetch all branches to get the latest changes
          git fetch origin "+refs/heads/*:refs/remotes/origin/*"
          
          # Get the latest commit in the default branch (usually 'main' or 'master')
          default_branch_commit=$(git rev-parse origin/main) # Change 'main' to your default branch name if needed

          # List changed files between the default branch and the current branch
          changed_files=$(git diff --name-only "$default_branch_commit")

          echo "::set-output name=files::${changed_files}"

      - name: Display absolute paths of changed files
        run: |
          changed_files="${{ steps.changed-files.outputs.files }}"
          absolute_paths=()

          # Iterate over changed files and get their absolute paths
          for file in $changed_files; do
            absolute_path=$(realpath "$file")
            absolute_paths+=("$absolute_path")
          done

          echo "Absolute paths of changed files:"
          for path in "${absolute_paths[@]}"; do
            echo "$path"
          done
