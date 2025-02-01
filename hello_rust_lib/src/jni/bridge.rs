use crate::functions::add::add;
use crate::functions::subtract::subtract;

#[no_mangle]
pub extern "C" fn Java_com_curtesmalteser_hellorust_MainActivity_add(
    _env: *mut std::ffi::c_void,
    _class: *mut std::ffi::c_void,
    left: i64,
    right: i64,
) -> i64 {
    add(left, right)
}

#[no_mangle]
pub extern "C" fn Java_com_curtesmalteser_hellorust_MainActivity_subtract(
    _env: *mut std::ffi::c_void,
    _class: *mut std::ffi::c_void,
    left: i64,
    right: i64,
) -> i64 {
    subtract(left, right)
}