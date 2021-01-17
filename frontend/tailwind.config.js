const colors = require('tailwindcss/colors')

module.exports = {
  purge: ['./src/**/*.html', './src/**/*.css'],
  darkMode: false, // or 'media' or 'class'
  theme: {
    colors: {
      white: colors.white,
      emerald: colors.emerald
    },
  },
  variants: {
    extend: {},
  },
  plugins: [],
}
