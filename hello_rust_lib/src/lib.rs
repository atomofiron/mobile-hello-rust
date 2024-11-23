#[no_mangle]
pub extern "C" fn Java_com_curtesmalteser_hellorust_MainActivity_add(
    _env: *mut std::ffi::c_void,
    _class: *mut std::ffi::c_void,
    left: i64,
    right: i64,
) -> i64 {
    left + right
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn it_works() {
        let result = add(2, 2);
        assert_eq!(result, 4);
    }
}
