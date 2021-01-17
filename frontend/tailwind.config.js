const colors = require('tailwindcss/colors')

module.exports = {
  purge: ['./src/**/*.html', './src/**/*.css'],
  darkMode: false, // or 'media' or 'class'
  theme: {
    colors: {
      white: colors.white,
      emerald: colors.emerald,
      lightblue: colors.lightBlue,
      orange: colors.orange
    },
  },
  variants: {
    extend: {},
  },
  plugins: [],
}
