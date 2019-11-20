<template>
  <div>
    <nav class="navbar" role="navigation" aria-label="main navigation">
        <div class="container">
          <div class="navbar-brand">
            <router-link :to="{name: 'home'}" exact class="navbar-item navbar-item-logo">
              <font-awesome-icon icon="shapes" size="lg" class="icon-shapes"></font-awesome-icon>
              <font-awesome-icon icon="child" size="lg" class="icon-child"></font-awesome-icon>
              <div class="brand-name">eli5</div>
            </router-link>
          </div>
          <div class="navbar-item navbar-item-search">
            <SearchInput class="search-input" :searchQuery="searchQuery" :category="category" @submit="onSearchSubmit"></SearchInput>
          </div>
        </div>
      </nav>
    <div class="section">
      <div class="container">
        <section v-if="answers != null && answers.length > 0" class="results">
          <p class="results-summary">Found {{response.data.numberOfElements}} matches of {{response.data.totalElements}} total for: "{{searchQuery}}" <span v-if="category !== ''">in {{categories[category].name}}</span></p>
          <ol class="answers" >
            <li v-for="answer in answers" :key="answer.id">
              <!-- TODO meter other como categoría en elastic -->
              <p>
                <category-tag
                    :category="categories[answer.question.category] || categories.other">
                </category-tag>
                <small class="question">{{answer.question.text}}</small>
              </p>
              <p>{{answer.text}}</p>
            </li>
          </ol>
        </section>
      </div>
    </div>
  </div>
</template>

<script>
import service from '@/common/service';
import CategoryTag from './CategoryTag.vue';
import SearchInput from './SearchInput.vue';
import categories from '../model/categories';

export default {
  name: 'Search',
  props: {
    searchQuery: {
      type: String,
      default: ''
    },
    category: {
      type: String,
      default: '',
      validator: value => [...Object.keys(categories), ''].includes(value)
    }
  },
  data: () => ({
    categories,
    response: null
  }),
  computed: {
    answers() {
      return this.response ? this.response.data.content : [];
    }
  },
  methods: {
    async fetchAnswers(searchQuery, category) {
      const results = await service.findAnswers(searchQuery || this.searchQuery, category || this.category);
      this.response = results;
    },
    async onSearchSubmit(q, c) {
      this.$router.push({ name: 'search', query: { q, c } })
          .catch(() => {
            this.fetchAnswers();
          });
    }
  },
  watch: {
    '$route'(to, from) {
      this.fetchAnswers();
    }
  },
  async created() {
    await this.fetchAnswers();
  },
  components: {
    CategoryTag,
    SearchInput
  },
};
</script>

<style lang="scss" scoped>
@import '@/scss/custom.scss';

.results-summary {
  margin-bottom: 1rem;
}

.answers {
  li {
    list-style-type: none;
    padding: 1rem;
    border-radius: 4px;
    background-color: white;
    box-shadow: 0px 0px 18px -8px rgba(0,0,0,0.75);

    &:not(:last-child) {
      margin-bottom: 1rem;
    }

    .question {
      margin-left: .5rem;
    }

    p:first-of-type {
      margin-bottom: .5rem;
    }
  }
}

/* navbar */

@media (min-width: $tablet) and (max-width: $desktop) {
  .icon-shapes {
    margin-left: 15px;
  }
}

.icon-child {
  margin-left: 5px;
}

.brand-name {
  margin-left: 5px;
  font-size: 1.5em;
  font-weight: 500;
}

.navbar-item-logo {
  margin: auto;
  flex-grow: 0;
}

.navbar-item-search {
  flex-grow: 1;
}

.search-input {
  width: 100%;
}
</style>
