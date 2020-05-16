//
//  ContentView.swift
//  sampleios
//
//  Created by Julius Canute on 11/5/20.
//  Copyright © 2020 Julius Canute. All rights reserved.
//

import SwiftUI

struct ContentView: View {
    @EnvironmentObject var configData: ConfigurationData
    var body: some View {
        NavigationView{
            VStack {
                Text(configData.text)
                    .font(.system(size: CGFloat(configData.size)))
                    .foregroundColor(configData.color)
                    .lineLimit(nil)
            }
            .navigationBarTitle(Text("MultiConfig Sample"),displayMode: .inline)
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
            .environmentObject(ConfigurationData(text: "Hello World!", visibility: true, color: .green, size: 16))
    }
}
