//
//  ContentView.swift
//  Hello Rust
//
//  Created by António Bastião on 15.03.2025.
//

import SwiftUI

struct ContentView: View {
    var hello: String {
        guard let cString = reverse_greet("dlroW tsuR") else { return "Error calling Rust" }
        defer { free_rust_string(UnsafeMutablePointer(mutating: cString)) }  // Ensure memory is freed after use
        return String(cString: cString) // Convert to Swift String
    }
    var body: some View {
        VStack {
            Image(systemName: "globe")
                .imageScale(.large)
                .foregroundStyle(.tint)
            Text(hello)
            Text("Sum from Rust: \(add(3, 2))")
            Text("Subtraction from Rust: \(subtract(3, 2))")
        }
        .padding()
    }
}

#Preview {
    ContentView()
}
