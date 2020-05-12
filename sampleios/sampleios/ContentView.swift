//
//  ContentView.swift
//  sampleios
//
//  Created by Julius Canute on 11/5/20.
//  Copyright © 2020 Julius Canute. All rights reserved.
//

import SwiftUI

struct ContentView: View {
    var body: some View {
        VStack {
            Text("Julius")
                .font(.title)
                .foregroundColor(.black)
            Text("Canute")
                .font(.subheadline)
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
