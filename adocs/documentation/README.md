title: Apache Isis

Notice:    Licensed to the Apache Software Foundation (ASF) under one
           or more contributor license agreements.  See the NOTICE file
           distributed with this work for additional information
           regarding copyright ownership.  The ASF licenses this file
           to you under the Apache License, Version 2.0 (the
           "License"); you may not use this file except in compliance
           with the License.  You may obtain a copy of the License at
           .
             http://www.apache.org/licenses/LICENSE-2.0
           .
           Unless required by applicable law or agreed to in writing,
           software distributed under the License is distributed on an
           "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
           KIND, either express or implied.  See the License for the
           specific language governing permissions and limitations
           under the License.

Isis documentation
-------------------------

Isis documentation uses [Asciidoc](http://www.methods.co.nz/asciidoc/). You're welcome to contribute.

License
-------
Apache Isis is licensed under ALv2.
See the LICENSE file for the full license text.

One time configuration
-----------------

Put the following information in your ~/.m2/settings.xml file

    <server>
      <id>isis-site</id>
      <username><YOUR_USERNAME></username>
      <password><YOUR_PASSWORD></password>
    </server>


Instant Preview (optional)
---------------

To build 

download ruby 2.0.0

* [http://rubyinstaller.org/downloads/](rubyinstaller.org/downloads)

> the wdm gem (required to monitor the filesystem if running on Windows) is not currently compatible with Ruby 2.1.

download and install devkit for the Ruby 2.0 installation:

* [http://rubyinstaller.org/downloads/](rubyinstaller.org/downloads)
* https://github.com/oneclick/rubyinstaller/wiki/Development-Kit

install:

    gem install bundler
    bundle install

run:

    ruby listen.rb

To review, recommend running a Python server:

    cd target/site
    python -m SimpleHTTPServer
    


Build and Review (using Maven)
-----------------------

To build the documentation locally prior to release, simply use:

    mvn site

The site will be generated at `target/site/index.html`.

Then open the browser on [localhost:8000](http://localhost:8000/).

Publish procedure
-----------------

To publish the documentation at [Isis Site](http://isis.apache.org/) you have do the following steps:

To publish to [staging server](http://isis.staging.apache.org/docs), run:

    mvn clean site-deploy

Then log in to <https://cms.apache.org/isis/publish> and click on the `Submit` button.


Preview procedure
-----------------
Note that it is also possible to push to a ["preview" area](http://isis.staging.apache.org/preview/docs) on the staging server;
in general you can skip this unless you want to double-check your edits first:

    mvn clean site-deploy -Ppreview
