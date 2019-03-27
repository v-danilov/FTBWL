export const AuthorizationTextConstants = {
  // Name
  NAME_REQUIRED: 'Name is required',
  NAME_NOT_VALID: 'Name must be valid',
  // Email
  EMAIL_REQUIRED: 'E-mail is required',
  EMAIL_NOT_VALID: 'E-mail must be valid',
  // Username
  USERNAME_REQUIRED: 'Username required',
  USERNAME_NOT_VALID: 'Username must be valid',
  // Password
  PASSWORD_REQUIRED: 'Password required',
  PASSWORD_NOT_VALID: 'Password must be valid',
  // Password comparing
  PASSWORDS_DOES_NOT_MATCH: 'Passwords doesn\'t match',
  // For required filed
  FIELD_MUST_BE_NOT_EMPTY: 'Must be not empty'
}

// TODO 22.03.2019 перенести в общий файл?
export const AuthorizationEndPoints = {
  SIGN_UP: '/api/signUp',
  SIGN_IN: '/signIn',
  GET_CITIES_BY_PREFIX: '/api/getCitiesByPrefix'
}
