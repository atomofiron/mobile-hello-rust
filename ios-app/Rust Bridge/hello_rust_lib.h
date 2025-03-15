//
//  hello_rust_lib.h
//  Hello Rust
//
//  Created by António Bastião on 15.03.2025.
//

#ifndef hello_rust_lib_h
#define hello_rust_lib_h

#include <stdio.h>

#include <stdint.h>  // For fixed-width integer types

// Function declarations for Rust library
int64_t add(int64_t left, int64_t right);
int64_t subtract(int64_t left, int64_t right);
const char* reverse_greet(const char* greet);  // Returns a dynamically allocated C string

// Function to free allocated memory for returned C strings
void free_rust_string(char* ptr);

#endif /* hello_rust_lib_h */
