//
//  LoginViewController.swift
//  jiGit
//
//  Created by Aleksei Korshun on 08/02/2019.
//  Copyright © 2019 Aleksei Korshun. All rights reserved.
//

import UIKit
import Framezilla

class LoginViewController: UIViewController {

    private let presenter: LoginPresenter

    private lazy var sideInset: CGFloat = {
        return view.frame.width * 0.15
    }()

    private lazy var loginView: UITextField = {
        let view = UITextField()
        view.placeholder = "Enter your login"
        return view
    }()

    private lazy var passwordView: UITextField = {
        let view = UITextField()
        view.placeholder = "Enter your password"
        view.isSecureTextEntry = true
        return view
    }()

    private lazy var loginButton: UIButton = {
        let view = UIButton()
        view.setTitleColor(.black, for: .normal)
        view.setTitleColor(.black, for: .selected)
        view.setTitleColor(.gray, for: .disabled)
        view.setTitle("Login", for: UIControl.State.normal)
        view.isEnabled = false
        return view
    }()

    private lazy var progressIndicator: UIActivityIndicatorView = {
        let view = UIActivityIndicatorView(style: .gray)
        return view
    }()

    private lazy var errorView: UILabel = {
        let view = UILabel()
        view.textColor = .red
        view.numberOfLines = 0
        view.isHidden = true
        return view
    }()

    init(_ presenter: LoginPresenter) {
        self.presenter = presenter
        super.init(nibName: nil, bundle: nil)
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("Can't create with NSCoder")
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        loginButton.addTarget(self, action: #selector(clickLoginButton), for: .touchUpInside)
        loginView.addTarget(self, action: #selector(textDidChange), for: .editingChanged)
        passwordView.addTarget(self, action: #selector(textDidChange), for: .editingChanged)

        view.addSubviews(loginView, passwordView, loginButton, progressIndicator, errorView)
    }

    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()

        loginView.configureFrame { maker in
            maker.sizeToFit()
                 .bottom(to: view.nui_centerY, inset: 8)
                 .left(inset: sideInset)
                 .right(inset: sideInset)
        }

        passwordView.configureFrame { maker in
            maker.sizeToFit()
                 .top(to: view.nui_centerY, inset: 8)
                 .left(inset: sideInset)
                 .right(inset: sideInset)
        }

        loginButton.configureFrame { maker in
            maker.sizeToFit()
                 .top(to: passwordView.nui_bottom, inset: 16)
                 .centerX()
        }

        progressIndicator.configureFrame { maker in
            maker.size(width: loginButton.frame.height, height: loginButton.frame.height)
                 .left(to: loginButton.nui_right, inset: 8)
                 .top(to: loginButton.nui_top)
        }

        errorView.configureFrame { maker in
            maker.sizeToFit()
                 .top(to: loginButton.nui_bottom, inset: 8)
                 .left(to: loginView.nui_left)
                 .right(to: loginView.nui_right)
        }
    }

    func isLoginEnabled(isEnabled: Bool) {
        loginButton.isEnabled = isEnabled
    }

    func isError(isError: Bool, message: String? = "") {
        errorView.text = message
        errorView.sizeToFit()
        errorView.isHidden = !isError
    }

    func isLoading(isLoading: Bool) {
        loginButton.isEnabled = !isLoading
        loginView.isEnabled = !isLoading
        passwordView.isEnabled = !isLoading

        progressIndicator.showLoading(isLoading)
    }

    @objc private func clickLoginButton() {
        guard let login: String = loginView.text,
              let password: String = passwordView.text
        else {
            return
        }
        presenter.clickLoginButton(username: login, password: password)
    }

    @objc private func textDidChange() {
        guard let login: String = loginView.text,
              let password: String = passwordView.text
        else {
            return
        }
        presenter.credentialChanged(username: login, password: password)
    }
}
