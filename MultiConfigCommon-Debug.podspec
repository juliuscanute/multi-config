Pod::Spec.new do |s|
    version = ENV['LIB_VERSION'] || '1.0.21'
    s.name              = 'MultiConfigCommon-Debug'
    s.version           = version
    s.summary           = 'The MultiConfigCommon is a dependency of MultiConfig which enables you to configure App settings at runtime.'
    s.homepage          = 'https://github.com/juliuscanute/multi-config'

    s.author            = { 'Name' => 'juliuscanute@touchcapture.com' }
     s.license           = { :type => 'Apache License, Version 2.0',
                                                    :text => <<-LICENSE
                                                      Copyright (c) 2010 Google Inc.
                                                      Licensed under the Apache License, Version 2.0 (the "License");
                                                      you may not use this file except in compliance with the License.
                                                      You may obtain a copy of the License at
                                                        http://www.apache.org/licenses/LICENSE-2.0
                                                      Unless required by applicable law or agreed to in writing, software
                                                      distributed under the License is distributed on an "AS IS" BASIS,
                                                      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
                                                      See the License for the specific language governing permissions and
                                                      limitations under the License.
                                                    LICENSE
                               }

    s.platform          = :ios
    s.source            = { :http => "https://github.com/juliuscanute/multi-config/releases/download/"+version+"/MultiConfigCommon-Debug.zip" }
    s.swift_versions = '5.0'
    s.ios.deployment_target = '13.0'
    s.ios.vendored_frameworks = "MultiConfigCommon.framework"
end