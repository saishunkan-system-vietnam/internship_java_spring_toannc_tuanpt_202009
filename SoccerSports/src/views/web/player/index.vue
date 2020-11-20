<template>
  <div class="container">
    <v-row>
      <v-col cols="2" md="2" xm="2"></v-col>
      <v-col cols="10" md="10" xm="10">
        <h1>Players</h1>
        <v-row>
          <div v-for="(p, index) in players" :key="index">
            <template v-if="index < 3">
              <v-card max-width="350" class="mr-10">
                <v-img
                  src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
                  height="200px"
                ></v-img>

                <v-card-title> {{ p.name }} </v-card-title>

                <v-card-subtitle> 1,000 miles of wonder </v-card-subtitle>

                <v-card-actions>
                  <v-btn color="orange lighten-2" text> Explore </v-btn>

                  <v-spacer></v-spacer>

                  <v-btn icon @click="show = !show">
                    <v-icon>{{
                      show ? "mdi-chevron-up" : "mdi-chevron-down"
                    }}</v-icon>
                  </v-btn>
                </v-card-actions>

                <v-expand-transition>
                  <div v-show="show">
                    <v-divider></v-divider>

                    <v-card-text>
                      I'm a thing. But, like most politicians, he promised more
                      than he could deliver. You won't have time for sleeping,
                      soldier, not with all the bed making you'll be doing. Then
                      we'll go with that data file! Hey, you add a one and two
                      zeros to that or we walk! You're going to do his laundry?
                      I've got to find a way to escape.
                    </v-card-text>
                  </div>
                </v-expand-transition>
              </v-card>
            </template>
          </div>
        </v-row>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="2" md="2" xm="2"></v-col>
      <v-col cols="10" md="10" xm="10">
        <h1>Players</h1>
        <v-row>
          <div v-for="(p, index) in players" :key="index">
            <template v-if="index < 3">
              <v-card max-width="350" class="mr-10">
                <v-img
                  src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
                  height="200px"
                ></v-img>

                <v-card-title> {{ p.name }} </v-card-title>

                <v-card-subtitle> 1,000 miles of wonder </v-card-subtitle>

                <v-card-actions>
                  <v-btn color="orange lighten-2" text> Explore </v-btn>

                  <v-spacer></v-spacer>

                  <v-btn icon @click="show = !show">
                    <v-icon>{{
                      show ? "mdi-chevron-up" : "mdi-chevron-down"
                    }}</v-icon>
                  </v-btn>
                </v-card-actions>

                <v-expand-transition>
                  <div v-show="show">
                    <v-divider></v-divider>

                    <v-card-text>
                      I'm a thing. But, like most politicians, he promised more
                      than he could deliver. You won't have time for sleeping,
                      soldier, not with all the bed making you'll be doing. Then
                      we'll go with that data file! Hey, you add a one and two
                      zeros to that or we walk! You're going to do his laundry?
                      I've got to find a way to escape.
                    </v-card-text>
                  </div>
                </v-expand-transition>
              </v-card>
            </template>
          </div>
        </v-row>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="2" md="2" xm="2"></v-col>
      <v-col cols="10" md="10" xm="10">
        <h1>Players</h1>
        <v-row>
          <div v-for="(p, index) in players" :key="index">
            <template v-if="index < 3">
              <v-card max-width="350" class="mr-10">
                <v-img
                  src="https://cdn.vuetifyjs.com/images/cards/sunshine.jpg"
                  height="200px"
                ></v-img>

                <v-card-title> {{ p.name }} </v-card-title>

                <v-card-subtitle> 1,000 miles of wonder </v-card-subtitle>

                <v-card-actions>
                  <v-btn color="orange lighten-2" text> Explore </v-btn>

                  <v-spacer></v-spacer>

                  <v-btn icon @click="show = !show">
                    <v-icon>{{
                      show ? "mdi-chevron-up" : "mdi-chevron-down"
                    }}</v-icon>
                  </v-btn>
                </v-card-actions>

                <v-expand-transition>
                  <div v-show="show">
                    <v-divider></v-divider>

                    <v-card-text>
                      I'm a thing. But, like most politicians, he promised more
                      than he could deliver. You won't have time for sleeping,
                      soldier, not with all the bed making you'll be doing. Then
                      we'll go with that data file! Hey, you add a one and two
                      zeros to that or we walk! You're going to do his laundry?
                      I've got to find a way to escape.
                    </v-card-text>
                  </div>
                </v-expand-transition>
              </v-card>
            </template>
          </div>
        </v-row>
      </v-col>
    </v-row>
  </div>
</template>
<script>
export default {
  data() {
    return {
      show: false,
      players: [],
    };
  },

  mounted() {
    this.getPlayers();
  },

  methods: {
    getPlayers() {
      let self = this;
      this.$store
        .dispatch("member/members")
        .then(function (response) {
          if (response.data.code == 0) {
            self.players = response.data.payload;
          } else {
            console.log("Run 1");
          }
        })
        .catch(function (error) {
          alert(error);
        });
    },
  },
};
</script>