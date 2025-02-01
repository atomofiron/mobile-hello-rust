#[inline]
pub fn reverse_greet(greet: &str) -> String {
    let revese_str: String = greet.chars().rev().collect();
    format!("Hello, {}!", revese_str)
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn it_works() {
        let result = reverse_greet("dlrow");
        assert_eq!(result, "Hello, world!");
    }
}
