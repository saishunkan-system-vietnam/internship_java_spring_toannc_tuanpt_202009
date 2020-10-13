<template>
  <v-card>
    <v-container>
      <v-icon large color="green darken-2"> mdi-soccer </v-icon>
      <b-row>
        <b-col cols="12" sm="2">
          <v-img height="100" width="100" :src="team.logo"> </v-img>
        </b-col>
        <b-col
          cols="12"
          sm="2"
          style="
            height: 100px;
            line-height: 100px;
            white-space: nowrap;
            font-size: 25px;
            color: #84705b;
          "
        >
          {{ team.nameTeam }}
        </b-col>
        <v-tabs
          v-model="tab"
          class="p-1"
          fixed-tabs
          background-color="#95afca"
          color="Black"
        >
          <v-tabs-slider></v-tabs-slider>
          <v-tab>Summary</v-tab>
          <v-tab>Fixtures</v-tab>
          <v-tab>Results</v-tab>
          <v-tab>Squad</v-tab>
        </v-tabs>
        <v-tabs-items v-model="tab" style="margin-top: 50px">
          <v-tab-item>
            <v-container>
              <h4>Lastest Scores</h4>

              <div v-for="(item, index1) in results" :key="index1">
                <template v-if="index1 < 5">
                    {{item.nameTour}}
                  <v-simple-table>
                    <template v-slot:default>
                      <tbody>
                        <tr
                          v-for="(item, index2) in item.schedule"
                          color="primary"
                          :key="index2"
                          v-b-popover.hover.bottom="'Kích vào để xem chi tiết'"
                          @click="relatedMatch(item.idSchedule)"
                        >
                          <template v-if="index2 < 5">
                           <td>
                               {{item.timeEnd}}
                            </td>
                            <td>
                                {{item.team[0].nameTeam}}
                            </td>
                            <td>
                              {{ item.status == 2 ? item.scoreTeam1 : "?" }}
                              -
                              {{ item.status == 2 ? item.scoreTeam2 : "?" }}
                            </td>
                            <td>  {{item.team[0].nameTeam}}
                            </td>
                          </template>
                        </tr>
                      </tbody>
                    </template>
                  </v-simple-table>
                </template>
              </div>
            </v-container>
          </v-tab-item>
          <v-tab-item> sss </v-tab-item>
          <v-tab-item> sss </v-tab-item>
        </v-tabs-items>
      </b-row>
    </v-container>
  </v-card>
</template>
<script>
export default {
  data() {
    return {
      tab: null,
      team: "",
      results: "",
    };
  },
  created() {
    this.getDataTeam();
    this.getTourByTeam();
  },
  methods: {
    getDataTeam() {
      this.$store
        .dispatch("team/getById", this.$route.params.id)
        .then((res) => {
          this.team = res.data;
        });
    },
    getTourByTeam() {
      this.$store
        .dispatch("tournament/getTourByTeam", this.$route.params.id)
        .then((res) => {
          this.results = res.data;
        });
    },
  },
};
</script>