import axios from 'axios'

const BACKEND_URL = 'http://localhost:3001/api/v1'

const getNames = () => {
  return axios
          .get(BACKEND_URL + '/names?sort=amount')
          .then(response => response.data)
}

const exports = {
  getNames
}

export default exports