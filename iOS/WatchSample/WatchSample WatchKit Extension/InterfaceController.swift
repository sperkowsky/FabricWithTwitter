//
//  InterfaceController.swift
//  WatchSample WatchKit Extension
//
//  Created by Gareth Jones  on 11/26/14.
//  Copyright (c) 2014 Twitter. All rights reserved.
//

import WatchKit
import Foundation
import Crashlytics


class InterfaceController: WKInterfaceController {

    override init(context: AnyObject?) {
        // Initialize variables here.
        super.init(context: context)
        // Display a tweet
        Crashlytics.startWithAPIKey("CRASHLYTICS_KEY")

    }

    override func willActivate() {
        // This method is called when watch view controller is about to be visible to user
        super.willActivate()
        NSLog("%@ will activate", self)
    }

    @IBAction func ForceCrash() {
        Crashlytics.sharedInstance().crash()
    }

    override func didDeactivate() {
        // This method is called when watch view controller is no longer visible
        NSLog("%@ did deactivate", self)
        super.didDeactivate()
    }

}
