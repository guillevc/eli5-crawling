import HTTP from './http';

export default {
  findAnswers(text, category) {
    console.log(`service searching questions text=${text} category=${category}`);
    return HTTP.get('/answers', { params: { text, category } });
  }
};
