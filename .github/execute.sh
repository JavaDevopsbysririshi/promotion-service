# git@github.com:JavaDevopsbysririshi/promotion-service.git

#!/bin/bash

# Define the output file for GitLab URLs
output_file="gitlab_urls.txt"

# Find all .hcl files and iterate through them
find . -type f -name "*.hcl" | while read -r hcl_file; do
    # Extract the folder path of the .hcl file
    folder_path="$(dirname "$hcl_file")"

    # Use awk to search for GitLab source URLs and save them to the output file
    awk '/source "gitlab" \{/,/repository_url *=/ {if (/repository_url *=/) {gsub(/[" ]/, "", $3); print "'"$folder_path"'/"$3}}' "$hcl_file"
done > "$output_file"

# Inform the user about the completion
echo "GitLab URLs extracted from .hcl files and saved in $output_file"

=======================================================


#!/bin/bash

# Set the directory where you want to search for .hcl files
search_directory="/path/to/your/directory"

# Define the output file
output_file="gitlab_urls.txt"

# Clear the output file if it exists
> "$output_file"

# Search for .hcl files and extract GitLab URLs
find "$search_directory" -type f -name "*.hcl" -exec awk -v OFS='\n' '
    BEGIN {
        in_block = 0
    }
    /source "gitlab"/ {
        in_block = 1
    }
    in_block && /repository_url/ {
        gsub("\"", "", $3)
        print $3
        in_block = 0
    }
' {} + >> "$output_file"

echo "GitLab URLs extracted and saved to $output_file"



===========================================




#!/bin/bash

# Function to search for GitLab source URLs in an HCL file
find_gitlab_urls() {
    local file="$1"
    local folder="$2"
    local urls=$(grep -E 'source "gitlab" \{' "$file" | grep -o 'repository_url\s*=\s*"[^"]*"' | sed -E 's/repository_url\s*=\s*"([^"]*)"/\1/')

    if [[ -n "$urls" ]]; then
        echo "In file: $folder/$file"
        echo "$urls"
    fi
}

# Main script
find . -type f -name "*.hcl" | while read -r file; do
    folder=$(dirname "$file")
    find_gitlab_urls "$file" "$folder"
done > gitlab_urls.txt


==========================================


#!/bin/bash

# Function to convert GitLab URL to GitHub URL
convert_to_github_url() {
    local gitlab_url="$1"
    local github_url="${gitlab_url/gitlab.com/github.com}"
    echo "$github_url"
}

# Function to process an HCL file
process_hcl_file() {
    local file="$1"
    local folder="$2"
    local gitlab_urls=$(grep -E 'source "gitlab" \{' "$file" | grep -o 'repository_url\s*=\s*"[^"]*"' | sed -E 's/repository_url\s*=\s*"([^"]*)"/\1/')

    if [[ -n "$gitlab_urls" ]]; then
        echo "In file: $folder/$file"
        while IFS= read -r gitlab_url; do
            github_url=$(convert_to_github_url "$gitlab_url")
            echo "GitHub URL: $github_url"
        done <<< "$gitlab_urls"
    fi
}

# Main script
find . -type f -name "*.hcl" | while read -r file; do
    folder=$(dirname "$file")
    process_hcl_file "$file" "$folder"
done > github_urls.txt



=================================

#!/bin/bash

# Function to convert GitLab URLs to GitHub URLs
convert_gitlab_to_github() {
    local gitlab_url="$1"
    # Replace GitLab domain with GitHub domain
    local github_url="${gitlab_url/gitlab.com/github.com}"
    echo "$github_url"
}

# Main script
find . -type f -name "*.hcl" | while read -r file; do
    folder=$(dirname "$file")
    gitlab_urls=$(grep -E 'source "gitlab" \{' "$file" | grep -o 'repository_url\s*=\s*"[^"]*"' | sed -E 's/repository_url\s*=\s*"([^"]*)"/\1/')

    if [[ -n "$gitlab_urls" ]]; then
        echo "In file: $folder/$file"
        while read -r gitlab_url; do
            github_url=$(convert_gitlab_to_github "$gitlab_url")
            echo "$folder/$file: $github_url"
        done <<< "$gitlab_urls"
    fi
done > github_urls.txt

===============================================


#!/bin/bash

# Check if a directory is provided as an argument
if [ $# -eq 0 ]; then
  echo "Usage: $0 <directory>"
  exit 1
fi

# Store the directory name in a variable
directory="$1"

# Use a loop to search for and retrieve lines containing 'source = *' in all .hcl files
for file in "$directory"/*.hcl; do
  if [ -f "$file" ]; then
    echo "Searching in $file:"
    awk '/source = .*/' "$file"
    echo "-----------------------------"
  fi
done

