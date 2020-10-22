<template>
  <v-container class="pt-2">
    <v-row>
      <v-col cols="12" sm="2" style="padding: 5px" class="pr-2">
        <v-card>
          <v-list style="background-color: white">
            
            <v-subheader><b style="color: red">TOURNAMENTS</b></v-subheader>
            <v-list-item v-for="item in items" :key="item.title">
              <v-list-item-content>
                <a
                  :href="
                    $router.resolve({
                      path: '/DetailTournamentBasketBall/' + item.idTour,
                    }).href
                  "
                  style="color: black"
                >
                   <v-list-item-title  v-b-popover.hover.top="item.nameTour">
                     {{
                    item.nameTour.length &lt; 10
                      ? item.nameTour
                      : item.nameTour.slice(0, 15) + "..."
                  }}</v-list-item-title
                ></a>
                
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-card>
      </v-col>
      <v-col cols="12" sm="8" style="padding: 0px" class="pt-1"
        ><router-view></router-view
      ></v-col>
      <v-col cols="12" sm="2" style="padding: 5px">
        <v-card>
          <v-list style="background-color: white">
            <v-subheader
              >
              <b style="color: green; min-width: 100px" class="text-center"
                > <a
                  :href="
                    $router.resolve({
                      path: '/RankTournament/BasketBall'
                    }).href
                  "
                >RANK</a></b
              ></v-subheader
            >
            <v-list-item v-for="(item, index) in rank" :key="index">
              <v-list-item-content>

                <a
                  :href="
                    $router.resolve({
                      path: '/DetailTeamBasketBall/' + item.idTeam,
                    }).href
                  "
                  style="color: black"
                >
                  <div>
                    <v-row>
                      <b-col sm="1">
                        <div
                          class="text-center"
                        >
                          {{ index + 1 }}
                        </div></b-col
                      >
                      <b-col sm="9">{{ item.name }}</b-col>
                    </v-row>
                  </div>
                </a>

              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
export default {
  data() {
    return {
      items: [],
      rank: [],
    };
  },
  created() {
    this.getTournament();
    this.getRank();
  },
  methods: {
    getTournament() {
      this.$store
        .dispatch("tournament/getByType", "BasketBall")
        .then((response) => {
          this.items = response.data;
        });
    },
    getRank() {
      this.$store
        .dispatch("tournament/getRank", "BasketBall")
        .then((response) => {
          this.rank = response.data;
        });
    },
  },
};
</script>