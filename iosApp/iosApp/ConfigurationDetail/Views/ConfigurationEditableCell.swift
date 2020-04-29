//
// Created by Julius Canute on 13/4/20.
//

import UIKit
import MultiConfigCommon

class ConfigurationEditableCell: UITableViewCell {
    var editableState: EditableState? {
        didSet {
            textLabel?.text = editableState?.description
            detailTextLabel?.text = editableState?.currentValue
        }
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: .subtitle, reuseIdentifier: reuseIdentifier)
    }
}
