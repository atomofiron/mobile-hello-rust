use crate::functions::{add, string, subtract};
use std::{ffi::{ CStr, CString}, os::raw::c_char};

#[no_mangle]
pub extern "C" fn add(a: i64, b: i64) -> i64 {
    self::add::add(a, b)
}

#[no_mangle]
pub extern "C" fn subtract(a: i64, b: i64) -> i64 {
    self::subtract::subtract(a, b)
}

#[no_mangle]
pub extern "C" fn reverse_greet(input: *const c_char) -> *mut c_char {
    let c_str = unsafe { CStr::from_ptr(input) };
    let rust_string = match c_str.to_str() {
        Ok(s) => self::string::reverse_greet(s),
        Err(_) => String::from(""), // ❌ Return an empty string if the input is not a valid UTF-8 string, this avoids panicking or allocating unnecesary memory
    };

    // Converts and returns the Rust String to a heap-allocated C string
    // The caller is responsible for freeing the memory allocated by this function
    // using the `free_rust_string` function
    CString::new(rust_string).unwrap().into_raw()
}

// Function to free Rust-allocated C strings
#[no_mangle]
pub extern "C" fn free_rust_string(ptr: *mut c_char) {
    if ptr.is_null() {
        return;
    }
    unsafe {
        drop(CString::from_raw(ptr));  // ✅ Reclaims ownership and deallocates memory
    }
}