////
//// Created by Julius Canute on 13/4/20.
////
//
//import Foundation
//

import UIKit


protocol ConfigurationSwitchCellDelegate {
    func onSwitchValueChange(cell: ConfigurationSwitchCell, currentValue: Bool)
}

class ConfigurationSwitchCell: UITableViewCell {
    var delegate: ConfigurationSwitchCellDelegate?
    var switchState: SwitchState? {
        didSet {
            let currentValue: Bool = switchState?.switchValue ?? false
            switchDescriptionLabel.text = switchState?.description
            switchUi.setOn(currentValue, animated: true)
        }
    }


    private let switchDescriptionLabel: UILabel = {
        let lbl = UILabel()
        lbl.textColor = .black
        lbl.font = UIFont.boldSystemFont(ofSize: 16)
        lbl.textAlignment = .left
        return lbl
    }()

    private let switchUi: UISwitch = {
        let uiSwitch = UISwitch()
        return uiSwitch
    }()

    @objc func changeSwitchState() {
        let currentValue: Bool = !switchUi.isOn
        switchUi.setOn(currentValue, animated: true)
        delegate?.onSwitchValueChange(cell: self, currentValue: currentValue)
    }

    override init(style: UITableViewCellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        addSubview(switchDescriptionLabel)
        addSubview(switchUi)
        switchDescriptionLabel.anchor(top: topAnchor, left: leftAnchor, bottom: bottomAnchor,
                right: switchUi.leftAnchor, paddingTop: 8, paddingLeft: 22, paddingBottom: 8, paddingRight: 8, width: 0,
                height: 0, enableInsets: false)
        switchUi.anchor(top: topAnchor, left: nil, bottom: bottomAnchor, right: rightAnchor,
                paddingTop: 8, paddingLeft: 0, paddingBottom: 8, paddingRight: 8, width: 0,
                height: 0, enableInsets: false)
        switchUi.addTarget(self, action: #selector(changeSwitchState), for: .touchUpInside)
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}