//
//  Colors.swift
//  ios
//
//  Created by Judith Jooste on 2023/08/27.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

extension Color {
    // https://docs.swift.org/swift-book/documentation/the-swift-programming-language/advancedoperators/
    // Bits 24-31 are alpha, 16-23 are red, 8-15 are green, 0-7 are blue
    // Example
    // if pink = 0xCC6699
    // redComponent = (pink & 0xFF0000) >> 16    --> redComponent is 0xCC, or 204
    // greenComponent = (pink & 0x00FF00) >> 8   --> greenComponent is 0x66, or 102
    // blueComponent = pink & 0x0000FF           --> blueComponent is 0x99, or 153
    init(hex: Int64, alpha: Double = 1) {
        self.init(
            .sRGB,
            red: Double((hex >> 16) & 0xff) / 255,
            green: Double((hex >> 08) & 0xff) / 255,
            blue: Double((hex >> 00) & 0xff) / 255,
            opacity: alpha
        )
    }
    
    private static let colors = Colors()
    static let lightBlue = Color(hex: colors.LightBlue)
    static let lightBlueGrey = Color(hex: colors.LightBlueGrey)
    static let accentViolet = Color(hex: colors.AccentViolet)
    static let textBlack = Color(hex: colors.TextBlack)
    static let darkGrey = Color(hex: colors.DarkGrey)
    
    static let primary = Color(light: .accentViolet, dark: .accentViolet)
    static let background = Color(light: .lightBlueGrey, dark: .darkGrey)
    static let onPrimary = Color(light: .white, dark: .white)
    static let onBackground = Color(light: .textBlack, dark: .white)
    static let surface = Color(light: .white, dark: .darkGrey)
    static let onSurface = Color(light: .textBlack, dark: .white)
}

private extension Color {
    init(light: Self, dark: Self) {
        self.init(uiColor: UIColor(light: UIColor(light), dark: UIColor(dark)))
    }
}

private extension UIColor {
    
    convenience init(light: UIColor, dark: UIColor) {
        self.init { traits in
            switch traits.userInterfaceStyle {
                case .light, .unspecified:
                    return light
                case .dark:
                    return dark
            @unknown default:
                return light
            }
        }
    }
    
}
