use crate::functions::{add::add, subtract::subtract};
use jni::JNIEnv;
use jni::objects::{JClass, JString};
use jni::sys::jstring;

#[no_mangle]
pub extern "system" fn Java_com_curtesmalteser_hellorust_MainActivity_add(
    _env: *mut std::ffi::c_void,
    _class: *mut std::ffi::c_void,
    left: i64,
    right: i64,
) -> i64 {
    add(left, right)
}

#[no_mangle]
pub extern "system" fn Java_com_curtesmalteser_hellorust_MainActivity_subtract(
    _env: *mut std::ffi::c_void,
    _class: *mut std::ffi::c_void,
    left: i64,
    right: i64,
) -> i64 {
    subtract(left, right)
}

#[no_mangle]
pub extern "system" fn Java_com_curtesmalteser_hellorust_MainActivity_reverseGreet<'local>(
    mut env: JNIEnv<'local>,
    _class: JClass<'local>,
    input: JString<'local>
) -> jstring {

    // Convert the Java JString to a Rust String
    let rust_string: String = env.get_string(&input)
        .expect("Couldn't get java string!")
        .into();

    // Call the Rust function to process the string
    let reversed_greeting: String = crate::functions::string::reverse_greet(&rust_string);  

    // Convert the Rust String back to a Java String (jstring)
    let output = env.new_string( reversed_greeting).expect("Couldn't create java string!");

    // Return the Java String (jstring)
    output.into_raw()
}
