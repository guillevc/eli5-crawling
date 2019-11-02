<template>
  <div class="section">
    <div class="container">
      <div class="wrapper">
        <figure class="image">
          <router-link :to="{name: 'home'}">
            <img src="../assets/explainlikeimfive.png">
          </router-link>
        </figure>
        <div class="field has-addons has-addons-centered">
          <p class="control">
            <span class="select">
              <select v-model="category">
                <option value=''>All categories</option>
                <option v-for="(value, key) in categories" :key="key" :value="key">{{value.name}}</option>
              </select>
            </span>
          </p>
          <p class="control is-expanded">
            <input v-model="searchQuery" class="input" type="text" placeholder="Ask a question">
          </p>
          <p class="control">
            <router-link :to="{name: 'search', query: { q: searchQuery, c: category }}" class="button is-primary">
              Search
            </router-link>
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import categories from '../model/categories';

export default {
  name: 'Home',
  data: () => ({
    categories,
    searchQuery: '',
    category: '',
  })
};
</script>

<style lang="scss" scoped>
@import '@/scss/custom.scss';

.section, .container, .wrapper {
  height: 100%;
}
.wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding-bottom: 100px;
}

.image {
  max-width: 400px;
  margin-bottom: 20px;
}

.field {
  min-width: 65%;
  margin: 0 auto;
}
</style>
