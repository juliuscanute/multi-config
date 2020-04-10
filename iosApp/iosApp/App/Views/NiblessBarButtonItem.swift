//
//  NiblessUIBarButtonItem.swift
//  iosApp
//
//  Created by Julius Canute on 10/4/20.
//

import Foundation
import UIKit

open class NiblessBarButtonItem: UIBarButtonItem {

  // MARK: - Methods
  public override init(frame: CGRect) {
    super.init(frame: frame)
  }

  @available(*, unavailable,
    message: "Loading this view from a nib is unsupported in favor of initializer dependency injection."
  )
  public required init?(coder aDecoder: NSCoder) {
    fatalError("Loading this view from a nib is unsupported in favor of initializer dependency injection.")
  }
}
