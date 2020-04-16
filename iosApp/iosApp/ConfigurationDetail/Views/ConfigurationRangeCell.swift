import UIKit
import app


protocol ConfigurationRangeCellDelegate {
    func saveIntConfiguration(key: String, currentValue: Int)
}

class ConfigurationRangeCell: UITableViewCell {
    var delegate: ConfigurationRangeCellDelegate?
    var progress: Int? = nil
    let minProgress: Int = 0
    let maxProgress: Int = 100
    var projection: Projection!
    var rangeState: RangeState? {
        didSet {
            switchDescriptionLabel.text = rangeState?.description
            rangeQuantity.text = rangeState?.getValueString()
            projection = Projection(userMax: Int32(rangeState?.max ?? 0), userMin: Int32(rangeState?.min ?? 0),
                    progressMax: Int32(maxProgress), progressMin: Int32(minProgress))
            projection.userValue = Int32(rangeState?.currentValue ?? 0)
            if progress == nil {
                progress = Int(projection!.progressValue)
            }
        }
    }
    private let switchDescriptionLabel: UILabel = {
        let lbl = UILabel()
        lbl.textColor = .black
        lbl.font = UIFont.boldSystemFont(ofSize: 16)
        lbl.textAlignment = .left
        return lbl
    }()
    private let decreaseButton: UIButton = {
        let btn = UIButton(type: .custom)
        btn.setImage(UIImage(named: "minus"), for: .normal)
        btn.imageView?.contentMode = .scaleAspectFill
        return btn
    }()

    private let increaseButton: UIButton = {
        let btn = UIButton(type: .custom)
        btn.setImage(UIImage(named: "plus"), for: .normal)
        btn.imageView?.contentMode = .scaleAspectFill
        return btn
    }()
    var rangeQuantity: UILabel = {
        let label = UILabel()
        label.font = UIFont.boldSystemFont(ofSize: 16)
        label.textAlignment = .left
        label.text = "0"
        label.textColor = .black
        return label

    }()

    @objc func decreaseFunc() {
        if progress! > minProgress {
            progress! -= 1
            changeQuantity()
        }
    }

    @objc func increaseFunc() {
        if progress! < maxProgress {
            progress! += 1
            changeQuantity()
        }
    }

    func changeQuantity() {
        projection.progressValue = Int32(progress!)
        rangeQuantity.text = String(projection.userValue)
        delegate?.saveIntConfiguration(key: rangeState!.key, currentValue: Int(projection!.userValue))
    }

    override init(style: UITableViewCellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        addSubview(switchDescriptionLabel)
        let stackView = UIStackView(arrangedSubviews: [decreaseButton, rangeQuantity, increaseButton])
        stackView.distribution = .equalSpacing
        stackView.axis = .horizontal
        stackView.spacing = 5
        addSubview(stackView)
        switchDescriptionLabel.anchor(top: topAnchor, left: leftAnchor, bottom: bottomAnchor, right: stackView.rightAnchor,
                paddingTop: 8, paddingLeft: 22, paddingBottom: 8, paddingRight: 8, width: 0,
                height: 0, enableInsets: false)
        stackView.anchor(top: topAnchor, left: nil, bottom: bottomAnchor, right: rightAnchor,
                paddingTop: 12, paddingLeft: 8, paddingBottom: 12, paddingRight: 8, width: 0,
                height: 0, enableInsets: false)
        increaseButton.addTarget(self, action: #selector(increaseFunc), for: .touchUpInside)
        decreaseButton.addTarget(self, action: #selector(decreaseFunc), for: .touchUpInside)
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}
