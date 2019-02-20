module.exports = (req, res, next) => {
  if (req.method == 'POST' && req.path == '/signIn') {
    if (req.body.username === 'test' && req.body.password === 'test') {
      res.status(200).json({})
    } else {
      res.status(400).json({message: 'wrong password'})
    }
  } else {
    next()
  }
}
