# Name App

A simple toy app using React and Clojure. The frontend displays a table of names and amounts. The rows can be sorted by amount (descending) or alphabetically (ascending) using the buttons at the bottom and filtered using the text input field at the top. The API provides similar functionality as JSON data.

## Installation

Download the code and run `npm install` in the `/frontend` directory. The backend dependencies will be installed when the backend app is first run (see below for details). Create the names database by opening the Lein REPL in the `/backend` directory and running the following clojure statements (the first line opens the REPL on the command line):

    lein repl
    (in-ns 'name-app.db)
    (-main)
    (quit)

## Usage

In order to test the app in its current state, you will need to start the frontend development web server and the backend server separately.

### Frontend

To start the frontend server (needed to serve the frontend files):
1. Go to the `/frontend` directory
2. Run `npm start`

### Backend

To start the backend server:
1. Go to the `/backend` directory
2. Run `lein run`

The frontend server uses port 3000 and the backend server uses port 3001. If you only want to use the backend API, you can specify a different port by passing it as an argument to the `lein run` command. For example:

    lein run 4321

### API Documentation

Swagger documentation for the backend API is also served by the backend API. The documentation can be accessed (using the default port) at:

    http://localhost:3001/api/doc

The same information can be found in the following section (API endpoints)

## API Endpoints

### /api/v1/names

Returns an object containing an array of all recorded names and their amounts. Defaults to sorting name-amount pairs by name. Sorting options are \"amount\" and \"name\" and are passed as a query parameter with the key \"sort\", e.g. `http://localhost:3001/names?sort=name`.

Example response data:

    {
        "names": [
            {
                "name": "Andy",
                "amount": 1
            },
            {
                "name": "Benjamin",
                "amount": 3
            },
            {
                "name": "Cecilia",
                "amount": 5
            }
        ]
    }
          
### /api/v1/names/:name

Returns an object with the name and its amount. The amount is set to 0 if the name is not found in the database.

Example response data:

    {
        "name": "Eric",
        "amount": 0
    }

### /api/v1/total-names

Returns the sum total of all recorded name amounts.

Example response data:

    {
        "total": 211
    }


## Technology Stack

- [React](https://reactjs.org/)
- [Tailwind CSS](https://tailwindcss.com/)
- [Webpack](https://webpack.js.org/)
- [Clojure](https://clojure.org/)
- [Reitit](https://github.com/metosin/reitit)
- [Lein](https://leiningen.org/)
- [SQLite](https://www.sqlite.org/index.html)

## Remarks / To-Do List

- Currently, configuration is hardcoded (e.g. port numbers). These should be moved to configuration files or, ideally, a single configuration file.

- Static files should not be served by the development server. The Clojure backend could serve the built JS files but this is not ideal. Another option could be to have a proxy server in front which would serve the static files and direct API calls to the backend. A proxy server can also help with SSL termination, loadbalancing, etc.

- The API currenty supports cross-origin resource sharing (CORS). This is unnecessary, if the API is not public and the frontend and backend are hosted under the same domain.

- A simple table may not be the most optimal form for displaying the name data. Even a simple bar chart could better serve the needs of the user. This could be achieved using D3js or some other data visualization library.


## License

Copyright © 2021

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
