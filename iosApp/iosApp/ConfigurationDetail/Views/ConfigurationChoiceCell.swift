//
// Created by Julius Canute on 13/4/20.
//

import UIKit
import MultiConfigCommon

class ConfigurationChoiceCell: UITableViewCell {
    var choiceState: ChoiceState? {
        didSet {
            textLabel?.text = choiceState?.description
            detailTextLabel?.text = choiceState?.getSelectedItem()
        }
    }

    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: .subtitle, reuseIdentifier: reuseIdentifier)
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}
