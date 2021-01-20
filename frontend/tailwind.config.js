const colors = require('tailwindcss/colors')

module.exports = {
  purge: ['./src/**/*.html', './src/**/*.css'],
  darkMode: false,
  theme: {
    extend: {
      colors: {
        lightblue: colors.lightBlue,
        orange: colors.orange
      }
    }
  },
  variants: {
    extend: {},
  },
  plugins: [],
}
