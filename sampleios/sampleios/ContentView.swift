//
//  ContentView.swift
//  sampleios
//
//  Created by Julius Canute on 11/5/20.
//  Copyright © 2020 Julius Canute. All rights reserved.
//

import SwiftUI

struct ContentView: View {
    @State var text: String
    @State var visibility: Bool
    @State var color: Color
    @State var size: Int
    var body: some View {
        NavigationView{
            VStack {
                Text(text)
                    .font(.system(size: CGFloat(size)))
                    .foregroundColor(color)
                    .lineLimit(nil)
            }
            .navigationBarTitle(Text("MultiConfig Sample"),displayMode: .inline)
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView(text: "MultiPlatform", visibility: true, color: .red, size: 16)
    }
}
