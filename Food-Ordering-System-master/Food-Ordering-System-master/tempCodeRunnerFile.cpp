#include <iostream>
#include <string>
#include <vector>
#include <map>

using namespace std;

// Define a file class to represent each file in the file system
class File {
public:
    string name;
    int size;
    int start;
    int end;
    File(string n, int s, int st, int e) : name(n), size(s), start(st), end(e) {}
};

// Define a file system manager class to manage the file system
class FileSystemManager {
public:
    vector<int> free_blocks; // A vector to store the free blocks on the disk
    map<string, File*> files; // A map to store the files on the disk with their names as the key

    FileSystemManager(int disk_size) {
        for (int i = 0; i < disk_size; i++) {
            free_blocks.push_back(i);
        }
    }

    // Allocate space for a file using contiguous allocation scheme
    File* allocate(string name, int size) {
        int num_blocks_needed = size;
        if (free_blocks.size() < num_blocks_needed) {
            return nullptr; // Not enough free blocks to allocate
        }
        int start_block = free_blocks[0];
        int end_block = free_blocks[num_blocks_needed-1];
        free_blocks.erase(free_blocks.begin(), free_blocks.begin() + num_blocks_needed);
        File* new_file = new File(name, size, start_block, end_block);
        files[name] = new_file;
        return new_file;
    }

    // Delete a file from the disk
    void delete_file(string name) {
        if (files.find(name) == files.end()) {
            return; // File not found
        }
        File* file = files[name];
        for (int i = file->start; i <= file->end; i++) {
            free_blocks.push_back(i);
        }
        files.erase(name);
        delete file;
    }

    // Rename a file on the disk
    void rename_file(string old_name, string new_name) {
        if (files.find(old_name) == files.end()) {
            return; // File not found
        }
        File* file = files[old_name];
        file->name = new_name;
        files.erase(old_name);
        files[new_name] = file;
    }

    // Move a file to a new location on the disk
    void move_file(string name, int new_start) {
        if (files.find(name) == files.end()) {
            return; // File not found
        }
        File* file = files[name];
        int num_blocks_needed = file->size;
        vector<int> new_free_blocks;
        bool found = false;
        for (int i = 0; i < free_blocks.size(); i++) {
            if (free_blocks[i] == new_start && !found) {
                for (int j = new_start; j < new_start+num_blocks_needed; j++) {
                    if (find(free_blocks.begin(), free_blocks.end(), j) == free_blocks.end()) {
                        return; // Not enough contiguous free blocks to move the file
                    }
                }
                file->start = new_start;
                file->end = new_start+num_blocks_needed-1;
                found = true;
            }
            if (free_blocks[i] < new_start || free_blocks[i] >= new_start+num_blocks_needed) {
                new_free_blocks.push_back(free_blocks[i]);
            }
        }
        if (!found) {
            return; // New start position not found
        }
    }
};